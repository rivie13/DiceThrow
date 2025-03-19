package edu.temple.dicethrow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager

        // Add the first die fragment (default 6 sides)
        if (savedInstanceState == null) {
            val dieFragment1 = DieFragment.newInstance()
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView1, dieFragment1)
                .commit()

            // Add the second die fragment (8 sides)
            val dieFragment2 = DieFragment.newInstance(8)
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView2, dieFragment2)
                .commit()
        }

        // Set up the roll button click listener
        val rollButton = findViewById<Button>(R.id.rollButton)
        rollButton.setOnClickListener {
            // Find the fragments and call throwDie() on each
            val fragment1 = fragmentManager.findFragmentById(R.id.fragmentContainerView1) as? DieFragment
            val fragment2 = fragmentManager.findFragmentById(R.id.fragmentContainerView2) as? DieFragment

            fragment1?.throwDie()
            fragment2?.throwDie()
        }
    }
}