package com.sihproject.breeddetector.helper

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import com.sihproject.breeddetector.BuildConfig

private const val modelName = "gemini-1.5-flash"
private val apiKey = BuildConfig.API_KEY
val model = GenerativeModel(modelName, apiKey)

suspend fun testAi(bitmap: Bitmap,prompt: String): GenerateContentResponse {

     val input  = content { image(bitmap)
                            text(prompt)}
     val res  : GenerateContentResponse = model.generateContent(input)
    return  res;

}

