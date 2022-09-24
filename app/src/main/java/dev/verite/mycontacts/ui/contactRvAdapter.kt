package dev.verite.mycontacts.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dev.verite.mycontacts.R
import dev.verite.mycontacts.databinding.ContactListItemBinding
import dev.verite.mycontacts.models.Contact

class ContactRvAdapter(var contactList:List<Contact>):
    RecyclerView.Adapter<ContactsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        var binding =
            ContactListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        var contactsViewHolder= ContactsViewHolder(binding)
        return contactsViewHolder
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        var currentcontact=contactList.get(position)
        holder.binding.tvName.text=currentcontact.name
        holder.binding.tvPhoneNumber.text=currentcontact.phoneNumber
        holder.binding.tvEmail.text=currentcontact.email
        holder.binding.tvAddress.text=currentcontact.address
        Picasso.get().load(currentcontact.image)
            .placeholder(R.drawable.ic_baseline_person_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .resize(300,300)
            .centerCrop()
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(holder.binding.ivContact)

        val context = holder.itemView.context
        holder.binding.ivContact.setOnClickListener{
            Toast.makeText(context,"You have clicked on ${currentcontact.name}'s image", Toast.LENGTH_SHORT).show()
        }
        holder.binding.cvContact.setOnClickListener {
            val intent = Intent(context, viewContactActivity::class.java)
            intent.putExtra("NAME", currentcontact.name)
            intent.putExtra("EMAIL", currentcontact.email)
            intent.putExtra("IMAGE", currentcontact.image)
            intent.putExtra("PHONE_NUMBER", currentcontact.phoneNumber)
            intent.putExtra("ADDRESS", currentcontact.address)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}
class ContactsViewHolder(var binding:ContactListItemBinding):RecyclerView.ViewHolder(binding.root)