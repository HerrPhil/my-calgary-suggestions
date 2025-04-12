package com.example.mycalgarysuggestions.datasource

import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.model.ContentItem

object DataSource {

    val categoryItems = listOf(
        ContentItem.CategoryItem(ContentItem.CategoryType.PubsAndCafes),
        ContentItem.CategoryItem(ContentItem.CategoryType.Dispensaries),
        ContentItem.CategoryItem(ContentItem.CategoryType.CoffeeShops),
        ContentItem.CategoryItem(ContentItem.CategoryType.PhoRestaurants),
    )

    private val pubAndCafeItems = listOf(
        ContentItem.RecommendationItem(
            nameResource = R.string.courtyard_name,
            ratingResource = R.string.courtyard_rating,
            descriptionResource = R.string.courtyard_description,
            addressResource = R.string.courtyard_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.hose_and_hound_name,
            ratingResource = R.string.hose_and_hound_rating,
            descriptionResource = R.string.hose_and_hound_description,
            addressResource = R.string.hose_and_hound_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.garrison_name,
            ratingResource = R.string.garrison_rating,
            descriptionResource = R.string.garrison_description,
            addressResource = R.string.garrison_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.mugshotz_name,
            ratingResource = R.string.mugshotz_rating,
            descriptionResource = R.string.mugshotz_description,
            addressResource = R.string.mugshotz_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.bella_roma_name,
            ratingResource = R.string.bella_roma_rating,
            descriptionResource = R.string.bella_roma_description,
            addressResource = R.string.bella_roma_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.cadence_name,
            ratingResource = R.string.cadence_rating,
            descriptionResource = R.string.cadence_description,
            addressResource = R.string.cadence_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.reds_name,
            ratingResource = R.string.reds_rating,
            descriptionResource = R.string.reds_description,
            addressResource = R.string.reds_address
        )
    )

    private val dispensaryItems = listOf(
        ContentItem.RecommendationItem(
            nameResource = R.string.fivepoint_cannabis_bridgeland_name,
            ratingResource = R.string.fivepoint_cannabis_bridgeland_rating,
            descriptionResource = R.string.fivepoint_cannabis_bridgeland_description,
            addressResource = R.string.fivepoint_cannabis_bridgeland_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.fivepoint_cannabis_first_street_name,
            ratingResource = R.string.fivepoint_cannabis_first_street_rating,
            descriptionResource = R.string.fivepoint_cannabis_first_street_description,
            addressResource = R.string.fivepoint_cannabis_first_street_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.spiritleaf_name,
            ratingResource = R.string.spiritleaf_rating,
            descriptionResource = R.string.spiritleaf_description,
            addressResource = R.string.spiritleaf_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.mtkushmore_name,
            ratingResource = R.string.mtkushmore_rating,
            descriptionResource = R.string.mtkushmore_description,
            addressResource = R.string.mtkushmore_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.four20_name,
            ratingResource = R.string.four20_rating,
            descriptionResource = R.string.four20_description,
            addressResource = R.string.four20_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.budbar_name,
            ratingResource = R.string.budbar_rating,
            descriptionResource = R.string.budbar_description,
            addressResource = R.string.budbar_address
        )
    )

    private val coffeeShopItems = listOf(
        ContentItem.RecommendationItem(
            nameResource = R.string.philsebastian_name,
            ratingResource = R.string.philsebastian_rating,
            descriptionResource = R.string.philsebastian_description,
            addressResource = R.string.philsebastian_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.bono_name,
            ratingResource = R.string.bono_rating,
            descriptionResource = R.string.bono_description,
            addressResource = R.string.bono_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.soughtxfound_name,
            ratingResource = R.string.soughtxfound_rating,
            descriptionResource = R.string.soughtxfound_description,
            addressResource = R.string.soughtxfound_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.stoop_name,
            ratingResource = R.string.stoop_rating,
            descriptionResource = R.string.stoop_description,
            addressResource = R.string.stoop_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.rosso_name,
            ratingResource = R.string.rosso_rating,
            descriptionResource = R.string.rosso_description,
            addressResource = R.string.rosso_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.analog_name,
            ratingResource = R.string.analog_rating,
            descriptionResource = R.string.analog_description,
            addressResource = R.string.analog_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.bridge_name,
            ratingResource = R.string.bridge_rating,
            descriptionResource = R.string.bridge_description,
            addressResource = R.string.bridge_address
        )
    )

    private val phoRestaurantItems = listOf(
        ContentItem.RecommendationItem(
            nameResource = R.string.pho_kim_name,
            ratingResource = R.string.pho_kim_rating,
            descriptionResource = R.string.pho_kim_description,
            addressResource = R.string.pho_kim_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.van_son_name,
            ratingResource = R.string.van_son_rating,
            descriptionResource = R.string.van_son_description,
            addressResource = R.string.van_son_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.golden_bell_name,
            ratingResource = R.string.golden_bell_rating,
            descriptionResource = R.string.golden_bell_description,
            addressResource = R.string.golden_bell_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.minh_chau_name,
            ratingResource = R.string.minh_chau_rating,
            descriptionResource = R.string.minh_chau_description,
            addressResource = R.string.minh_chau_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.pho_hoai_name,
            ratingResource = R.string.pho_hoai_rating,
            descriptionResource = R.string.pho_hoai_description,
            addressResource = R.string.pho_hoai_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.bagolac_name,
            ratingResource = R.string.bagolac_rating,
            descriptionResource = R.string.bagolac_description,
            addressResource = R.string.bagolac_address
        ),
        ContentItem.RecommendationItem(
            nameResource = R.string.noodle_king_name,
            ratingResource = R.string.noodle_king_rating,
            descriptionResource = R.string.noodle_king_description,
            addressResource = R.string.noodle_king_address
        )

    )

    fun getRecommendations(category: ContentItem.CategoryType): List<ContentItem.RecommendationItem> {
        return when (category) {
            ContentItem.CategoryType.PubsAndCafes -> pubAndCafeItems
            ContentItem.CategoryType.Dispensaries -> dispensaryItems
            ContentItem.CategoryType.CoffeeShops -> coffeeShopItems
            ContentItem.CategoryType.PhoRestaurants -> phoRestaurantItems
            ContentItem.CategoryType.NoCategory -> listOf()
        }
    }

}