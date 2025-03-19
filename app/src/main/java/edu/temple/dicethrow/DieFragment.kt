package edu.temple.dicethrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class DieFragment : Fragment() {

    companion object {
        private const val DIE_SIDES_KEY = "sidenumber"
        private const val DIE_VALUE_KEY = "die_value"

        // Factory method to create DieFragment with a specific number of sides
        fun newInstance(numSides: Int = 6): DieFragment {
            val fragment = DieFragment()
            val args = Bundle().apply {
                putInt(DIE_SIDES_KEY, numSides)
            }
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var dieTextView: TextView
    var dieSides: Int = 6
    private var dieValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIE_SIDES_KEY, 6) // Default to 6 if not provided
        }
        // Restore the dieValue if it exists in the savedInstanceState
        if (savedInstanceState != null) {
            dieValue = savedInstanceState.getInt(DIE_VALUE_KEY, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // If it's the first time, throw the die. Otherwise, display the saved value.
        if (savedInstanceState == null) {
            throwDie()
        } else {
            dieTextView.text = dieValue.toString()
        }

        view.setOnClickListener {
            throwDie()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the current dieValue to the bundle
        outState.putInt(DIE_VALUE_KEY, dieValue)
    }

    fun throwDie() {
        dieValue = Random.nextInt(dieSides) + 1
        dieTextView.text = dieValue.toString()
    }
}