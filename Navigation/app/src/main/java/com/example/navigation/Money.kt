package com.example.navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.text.DecimalFormat

@Parcelize
data class Money(val amount: BigDecimal) : Parcelable {
    fun getValue(value: BigDecimal?): String? {
        val df = DecimalFormat("##,##,###.##")
        return java.lang.String.valueOf(df.format(value))
    }
}