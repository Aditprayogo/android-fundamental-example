package com.aditprayogo.myunittesting

class MainViewModel(private val cuboidModel: CuboidModel) {

    fun getCircumference(): Double = cuboidModel.getCircumference()

    fun getSurfaceArea(): Double = cuboidModel.getSurfaceArea()

    fun getvolume(): Double = cuboidModel.getVolume()

    fun save(l: Double, w: Double, h:Double){
        cuboidModel.save(l,w,h)
    }
}