package pl.mdanilowski.myapplication.data

import javax.inject.Inject

class ApiRepository @Inject constructor(apiService: ApiService) {

    private lateinit var apiService: ApiService

}