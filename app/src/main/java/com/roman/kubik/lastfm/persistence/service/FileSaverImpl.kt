package com.roman.kubik.lastfm.persistence.service

import android.content.Context
import android.os.Environment
import java.io.File
import javax.inject.Inject
import java.io.FileOutputStream
import java.net.URL


class FileSaverImpl @Inject constructor(private val context: Context) : FileSaver {

    override fun loadFile(url: String, fileName: String): String? {

        val input = URL(url).openStream()
        val filePath: String
        try {
            filePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath + "/$fileName.png"

            val output = FileOutputStream(filePath)

            try {
                val buffer = ByteArray(2048)
                var bytesRead: Int
                do {
                    bytesRead = input.read(buffer, 0, buffer.size)
                    if (bytesRead < 0)
                        break

                    output.write(buffer, 0, bytesRead)
                } while (true)

            } finally {
                output.close()
            }
        } finally {
            input.close()
        }
        return filePath
    }

    override fun deleteFile(fileName: String?): Boolean {
        return if (fileName != null) {
            val file = File(fileName)
            file.delete()
        } else {
            true
        }
    }
}