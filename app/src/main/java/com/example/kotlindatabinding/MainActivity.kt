package com.example.kotlindatabinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindatabinding.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : (ActivityMainBinding) = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // setting title (TextView) on activity using data binding
        binding.mainActivityViewModel = MainActivityModel("Hobby List")

        // setting linear layout manager for recyclerview
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL

        // initializing adapter
        val adapter = MyAdapter(DummyMDataSupplier.hobbies, this)

        // attaching layout & adapter to recyclerview
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    override fun onClick(hobby: HobbyModel) {
        Toast.makeText(this, hobby.hobby, Toast.LENGTH_SHORT).show()

        // implicit intent
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, "my hobby is ${hobby.hobby}" )
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent, "Share via:"))
    }
}