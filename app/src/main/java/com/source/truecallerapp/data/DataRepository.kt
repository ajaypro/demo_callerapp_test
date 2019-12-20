package com.source.truecallerapp.data

import com.source.truecallerapp.utils.Constants.OK
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.util.*

class DataRepository(private val dataSource :DataSource) {


    private lateinit var result: Result

    suspend fun fetchTenthCharacter(): Result {

        val response = dataSource.fetchData()
        var char = 0
        when (response.code()) {
            OK -> {
                if (response.isSuccessful) {
                    val stream = response.body()?.byteStream()
                    val reader = InputStreamReader(stream, "UTF-8")
                    for (i in 0..9) {
                        char = reader.read()
                    }
                } else {
                    result = Result.Error(Throwable(response.errorBody().toString()))
                }

                result = Result.Success("10th CHARACTER - $char")
            }

            else -> {
                result = Result.NetworkError.castToNetworkError(response.code())
            }

        }
        return result
    }

    suspend fun fetchEveryTenthcharacter(): Result {
        var data = ""
        val response = dataSource.fetchData()
        val stringBuilder = StringBuilder()

        when (response.code()) {
            OK -> {
                if (response.isSuccessful) {
                    val stream = response.body()?.byteStream()
                    val reader = InputStreamReader(stream, "UTF-8")
                    var i = 0
                    var j = 0
                    var char: Int
                    while (reader.read()
                            .also { char = it } != -1
                    ) {
                        if (i > 0 && i % 10 == 0) {
                            stringBuilder.append(char.toChar())
                            if (j > 0 && j % 10 == 0) stringBuilder.append(' ')
                            j++
                        }
                        i++
                    }
                    data = stringBuilder.append("'").toString()
                } else {
                    result = Result.Error(Throwable(response.errorBody().toString()))
                }
                result = Result.Success("EVERY 10TH CHARACTER ->  $data")
            }

            else -> {
                result = Result.NetworkError.castToNetworkError(response.code())
            }
        }
        return result
    }

    suspend fun fetchWordCount(): Result {
        var data = ""
        val response = dataSource.fetchData()

        when (response.code()) {
            OK -> {
                if (response.isSuccessful) {
                    val stream = response.body()?.byteStream()

                    val reader: Reader = InputStreamReader(stream, "UTF-8")
                    val buffer = BufferedReader(reader)
                    val wordCount: MutableMap<String, Int?> = TreeMap()
                    var line: String?
                    while (buffer.readLine().also { line = it } != null) {

                        val tokens = line!!.split("\\W+").toTypedArray()

                        for (element in tokens) {
                            var item = element
                            if (item == "") continue
                            item = item.toLowerCase(Locale.ENGLISH)

                            var countValue = if (wordCount.containsKey(item)) wordCount[item]!! else 0
                            countValue++
                            wordCount[item] = countValue
                        }
                    }
                    val stringBuilder = StringBuilder()
                    for ((key, value) in wordCount) {
                        stringBuilder.append(key)
                            .append("  COUNT :  ")
                            .append(value)
                            .append("\n")
                    }
                    data = stringBuilder.toString()
                } else {
                    result = Result.Error(Throwable(response.errorBody().toString()))
                }
                result = Result.Success("EVERY WORD COUNT ->  $data")
            }
            else -> {
                result = Result.NetworkError.castToNetworkError(response.code())
            }
        }
        return result
    }


}





