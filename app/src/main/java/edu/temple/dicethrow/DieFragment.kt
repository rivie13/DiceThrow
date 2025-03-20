package edu.temple.dicethrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    companion object {
        private const val DIE_SIDES_KEY = "sidenumber"
        private const val DIE_VALUE_KEY = "die_value"
    }

    lateinit var dieTextView: TextView
    var dieSides: Int = 6
    private var dieValue: Int = 0

    lateinit var dieViewModel: DieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIE_SIDES_KEY, 6) // Default to 6 if not provided
        }
        // Restore the dieValue if it exists in the savedInstanceState
        if (savedInstanceState != null) {
            dieValue = savedInstanceState.getInt(DIE_VALUE_KEY, 0)
        }
        dieViewModel = ViewModelProvider(this.requireActivity())[DieViewModel::class.java]
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

        dieViewModel.getCurrentRoll().observe(viewLifecycleOwner) {
            dieValue = it
            dieTextView.text = it.toString()
        }
        if (dieViewModel.getCurrentRoll().value == null) {
            throwDie()
        }
    }



    fun throwDie() {
        dieViewModel.setCurrentRoll(Random.nextInt(dieSides) + 1)
    }
}