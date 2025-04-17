package com.example.mycalgarysuggestions.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mycalgarysuggestions.R

sealed class ContentItem {

    enum class CategoryType(@StringRes val title: Int, @DrawableRes val icon: Int) {
        PubsAndCafes(title = R.string.pubs_and_cafes, icon = R.drawable.pub),
        Dispensaries(title = R.string.dispensaries, icon = R.drawable.marijuana),
        CoffeeShops(title = R.string.coffee_shops, icon = R.drawable.coffee),
        PhoRestaurants(title = R.string.pho_restaurants, icon = R.drawable.pho),
    }

    data class CategoryItem(val categoryType: CategoryType) : ContentItem()

    data class RecommendationItem(
        @StringRes val nameResource: Int = 0,
        @StringRes val ratingResource: Int = 0,
        @StringRes val descriptionResource: Int = 0,
        @StringRes val addressResource: Int = 0,
        @DrawableRes val iconResource: Int = 0,
        @DrawableRes val logoResource: Int?
    ) : ContentItem()

}
