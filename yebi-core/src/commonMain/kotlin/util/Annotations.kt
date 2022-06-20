package com.elouyi.yebi.util

@RequiresOptIn("Yebi internal api", level = RequiresOptIn.Level.WARNING)
public annotation class YebiInternalAPI

@RequiresOptIn("Yebi experimental API", level = RequiresOptIn.Level.ERROR)
public annotation class YebiExperimentalAPI
