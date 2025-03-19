package com.example.myapplication

class Utils {
    companion object {
        fun calculateWarmups(workingWeight: Double, percentages: List<Double>): List<Double> {
            return percentages.map { percent -> (workingWeight * percent).toInt().toDouble() }
        }

        fun calculatePlates(weight: Double, barWeight: Double, availablePlates: List<Double>): List<Double> {
            val weightPerSide = (weight - barWeight) / 2
            val plates = mutableListOf<Double>()
            var remainingWeight = weightPerSide

            for (plate in availablePlates.sortedDescending()) {
                while (remainingWeight >= plate) {
                    plates.add(plate)
                    remainingWeight -= plate
                }
            }
            return plates
        }
    }
}