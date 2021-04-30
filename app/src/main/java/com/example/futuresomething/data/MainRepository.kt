package com.example.futuresomething.data

import com.example.futuresomething.R
import com.example.futuresomething.domain.Film

class MainRepository {

    val filmsDataBase = listOf(
            Film(
                    0,
                    "Willy Wonka",
                    R.drawable.willy_wonka,
                    "Willy Wonka is extraordinary. He's a chocolate-making genius who relishes nonsense. He can't abide ugliness in factories. And he likes to make mischief, even if that means talking to the President of the United States whilst pretending to be a man from Mars, as he does in Charlie and the Great Glass Elevator.",
                    5.7f
            ),
            Film(1, "JOKER", R.drawable.joker, "This should be a description", 8.7f),
            Film(3, "INTERSTELLAR", R.drawable.interstellar, "This should be a description", 4.5f),
            Film(4, "The Thing", R.drawable.the_thing, "This should be a description", 6.9f),
            Film(5, "The Godfather", R.drawable.the_godfather, "This should be a description", 8.5f),
            Film(6, "Akira", R.drawable.akira, "This should be a description", 6f),
            Film(
                    7,
                    "The Shawshank Redemption",
                    R.drawable.the_shawshank_redemption,
                    "This should be a description",
                    5.4f
            ),
            Film(8, "Deerskin", R.drawable.deerskin, "This should be a description", 3.5f)

    )
}