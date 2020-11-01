package com.victorvgc.fullhouser.flowOne.failure

import com.victorvgc.fullhouser.core.utils.Failure
import kotlinx.android.parcel.Parcelize

@Parcelize
class SomethingWentWrongFailure: Failure("Ops! Something went wrong :(")