package com.example.mycalgarysuggestions.model

import androidx.annotation.StringRes
import com.example.mycalgarysuggestions.R

sealed class ContentItem {

    enum class CategoryType(@StringRes val title: Int) {
        NoCategory(title = R.string.default_category),
        PubsAndCafes(title = R.string.pubs_and_cafes),
        Dispensaries(title = R.string.dispensaries),
        CoffeeShops(title = R.string.coffee_shops),
        PhoRestaurants(title = R.string.pho_restaurants),
    }

    data class CategoryItem(val categoryType: CategoryType) : ContentItem()

    data class RecommendationItem(
        @StringRes val nameResource: Int = 0,
        @StringRes val ratingResource: Int = 0,
        @StringRes val descriptionResource: Int = 0,
        @StringRes val addressResource: Int = 0
    ) : ContentItem()

}