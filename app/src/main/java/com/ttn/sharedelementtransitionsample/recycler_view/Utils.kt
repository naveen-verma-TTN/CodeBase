package com.ttn.sharedelementtransitionsample.recycler_view

import android.content.Context
import com.ttn.sharedelementtransitionsample.R
import java.util.*

object Utils {
    fun generateAnimalItems(context: Context): ArrayList<AnimalItem> {
        val animalItems = ArrayList<AnimalItem>()
        animalItems.add(
            AnimalItem(
                "Dog",
                context.getString(R.string.dog_blurb),
                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1"
            )
        )
        animalItems.add(
            AnimalItem(
                "Penguin",
                context.getString(R.string.penguin_blurb),
                "https://c1.staticflickr.com/9/8616/16237154608_c5489cae31_z.jpg"
            )
        )
        animalItems.add(
            AnimalItem(
                "Eagle",
                context.getString(R.string.eagle_blurb),
                "https://c1.staticflickr.com/5/4010/4210875342_7cb06a9b62_z.jpg?zz=1"
            )
        )
        animalItems.add(
            AnimalItem(
                "Rabbit",
                context.getString(R.string.rabbit_blurb),
                "https://c2.staticflickr.com/4/3285/2819978026_175072995a_z.jpg?zz=1"
            )
        )
        animalItems.add(
            AnimalItem(
                "Dolphin",
                context.getString(R.string.dolphin_blurb),
                "https://c1.staticflickr.com/8/7619/16124006043_60bc4d8ca5_z.jpg"
            )
        )
        animalItems.add(
            AnimalItem(
                "Snek",
                context.getString(R.string.snek_blurb),
                "https://c1.staticflickr.com/9/8796/17158681740_a6caa5099f_z.jpg"
            )
        )
        animalItems.add(
            AnimalItem(
                "Seal",
                context.getString(R.string.seal_blurb),
                "https://c1.staticflickr.com/4/3852/14729534910_62b338dd72_z.jpg"
            )
        )
        animalItems.add(
            AnimalItem(
                "Rhino",
                context.getString(R.string.rhino_blurb),
                "https://c1.staticflickr.com/1/335/18040640224_f56f05f8dc_z.jpg"
            )
        )
        animalItems.add(
            AnimalItem(
                "Leopard",
                context.getString(R.string.leopard_blurb),
                "https://c1.staticflickr.com/9/8678/16645189230_b0e96e7af9_z.jpg"
            )
        )
        animalItems.add(
            AnimalItem(
                "Hippo",
                context.getString(R.string.hippo_blurb),
                "https://c2.staticflickr.com/4/3774/9377370000_6a57d1cfec_z.jpg"
            )
        )
        return animalItems
    }
}