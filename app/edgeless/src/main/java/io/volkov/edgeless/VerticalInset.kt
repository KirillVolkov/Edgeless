package io.volkov.edgeless

data class VerticalInset(val top: Int, val bottom: Int) {
    companion object {
        fun empty() = VerticalInset(0, 0)
    }
}