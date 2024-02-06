package com.test.testtrinitywizards.data.localasset

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.test.testtrinitywizards.data.dto.ContactDto

class ContactLocalAssetSource(
    private val context: Context
) {
    private fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    fun loadContactData(): List<ContactDto> {
        val inputStream = context.assets.open("data.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val jsonString = String(buffer, Charsets.UTF_8)
        Log.d("Test", jsonString)
        return gson().fromJson(jsonString, object: TypeToken<List<ContactDto>>() {}.type)
    }
}