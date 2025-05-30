package com.corsolp.uicompose.extensions

import androidx.annotation.StringRes
import com.corsolp.domain.models.RentalType
import com.corsolp.uicompose.R


@StringRes fun RentalType.toResId(): Int {
    return when (this) {
        is RentalType.Room -> R.string.room
        is RentalType.Apartment -> R.string.apartment
        is RentalType.Bed -> R.string.bed
    }
}