package com.elouyi.yebi.feature.user

import com.elouyi.yebi.feature.YebiAPI

public interface UserAPI : YebiAPI {

    public suspend fun login()
}