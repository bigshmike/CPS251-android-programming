package com.example.recycleview

class AppResources {

    private var titles = arrayOf(
        "Chapter One",
        "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven",
        "Chapter Eight"
    )

    private var details = arrayOf(
        "Item one details", "Item two details",
        "Item three details", "Item four details",
        "Item five details", "Item six details",
        "Item seven details", "Item eight details"
    )

    private var images = intArrayOf(
        R.drawable.android_image_1,
        R.drawable.android_image_2, R.drawable.android_image_3,
        R.drawable.android_image_4, R.drawable.android_image_5,
        R.drawable.android_image_6, R.drawable.android_image_7,
        R.drawable.android_image_8
    )

    // When AppResources is initialized, the arrays will be shuffled
    init {
        titles.shuffle()
        details.shuffle()
        images.shuffle()
    }

    // Getters and Setters
    fun getTitles(): Array<String> {
        return titles
    }

    fun setTitles(titles: Array<String>) {
        this.titles = titles
    }

    fun getDetails(): Array<String> {
        return details
    }

    fun setDetails(details: Array<String>) {
        this.details = details
    }

    fun getImages(): IntArray {
        return images
    }

    fun setImages(images: IntArray) {
        this.images = images
    }

}