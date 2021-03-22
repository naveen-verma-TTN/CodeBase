package com.ttn.advance_koin.view
/*
 * Created by Naveen Verma on 22/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ttn.advance_koin.databinding.LayoutUserListItemBinding
import com.ttn.advance_koin.model.entity.GithubUser
import java.util.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var users: List<GithubUser> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: LayoutUserListItemBinding =
            LayoutUserListItemBinding.inflate(
                layoutInflater, parent, false
            )
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ViewHolder).bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUsers(users: List<GithubUser>) {
        this.users = users


    }

    /**
     * Inner ViewHolder class
     */
    class ViewHolder(private val binding: LayoutUserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(githubUser: GithubUser) {
            binding.user = githubUser
        }
    }
}