package Departments

import com.google.gson.annotations.SerializedName

class Department {
    var typeId: Int = 0
    var isMain: Boolean = false
    @SerializedName("isActive")
    @get:SerializedName("isActive")
    var isActive: Boolean = false
    var name: String? = null
    var addressId: Int = 0
    var tables: List<Int>? = null
}
