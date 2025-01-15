package com.example.yalladrop.models

import java.time.LocalDateTime

class FoodItems (
    var  name : String ,
    var  price : Double ,
    var  numItem : Int ,
    var  date : LocalDateTime  ,
    var  painterId: Int,
    var id : String = "0",
)