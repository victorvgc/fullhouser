package com.victorvgc.fullhouser.flowOne.failure

import com.victorvgc.fullhouser.core.utils.Failure
import kotlinx.android.parcel.Parcelize

@Parcelize
class APIFailure: Failure("API Response: unsuccessful")