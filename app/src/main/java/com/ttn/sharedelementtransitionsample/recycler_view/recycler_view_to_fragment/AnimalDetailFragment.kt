package com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItem

class AnimalDetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal_detail, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val animalItem: AnimalItem =
            arguments!!.getParcelable(EXTRA_ANIMAL_ITEM)!!
        val transitionName =
            arguments!!.getString(EXTRA_TRANSITION_NAME)
        val detailTextView =
            view.findViewById<View>(R.id.animal_detail_text) as TextView
        detailTextView.text = animalItem.detail
        val imageView =
            view.findViewById<View>(R.id.animal_detail_image_view) as ImageView
        imageView.transitionName = transitionName
        Picasso.get()
            .load(animalItem.imageUrl)
            .noFade()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception) {
                    startPostponedEnterTransition()
                }
            })
    }

    companion object {
        private const val EXTRA_ANIMAL_ITEM = "animal_item"
        private const val EXTRA_TRANSITION_NAME = "transition_name"
        @JvmStatic
        fun newInstance(
            animalItem: AnimalItem?,
            transitionName: String?
        ): AnimalDetailFragment {
            val animalDetailFragment = AnimalDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_ANIMAL_ITEM, animalItem)
            bundle.putString(EXTRA_TRANSITION_NAME, transitionName)
            animalDetailFragment.arguments = bundle
            return animalDetailFragment
        }
    }
}