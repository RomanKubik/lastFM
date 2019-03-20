package com.roman.kubik.lastfm.persistence.service

interface FileSaver {

    fun loadFile(url: String, fileName: String): String?

    fun deleteFile(fileName: String?): Boolean
}
