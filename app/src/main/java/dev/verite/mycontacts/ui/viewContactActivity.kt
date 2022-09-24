package dev.verite.mycontacts.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dev.verite.mycontacts.R
import dev.verite.mycontacts.databinding.ActivityViewContactBinding

class viewContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        getExtras()
    }

    fun getExtras(){
        val extras = intent.extras
        val image = extras?.getString("IMAGE","")
        Picasso.get().load(image)
            .placeholder(R.drawable.ic_baseline_person_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .resize(300,300)
            .centerCrop()
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(binding.ivNamee)
        val name = extras?.getString("NAME","")
        binding.tvNamee.text = name
        val email = extras?.getString("EMAIL","")
        binding.tvEmaill.text = email
        val number = extras?.getString("PHONE_NUMBER","")
        binding.tvNumber.text = number
        val address = extras?.getString("ADDRESS","")
        binding.tvAddresse.text = address

        Toast.makeText(this,"$name, $email", Toast.LENGTH_SHORT).show()
    }
}