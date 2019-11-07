/*
 * Copyright (C) 2019 Ramana Tech Architect, Wishto Internet Private Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package `in`.sugary.slidegridview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.GridView
import androidx.annotation.RequiresApi

class SlideGridView : GridView {

    private var column: Int = 0
    /**
     * @return Number of rows associated with the view
     */
    /**
     * @param row sets the desired number of row in the grid
     */
    var numRows: Int = 0

    private var itemWidth = 0
    private var itemHeight = 0

    private var slideGridAdapter: SlideGridAdapter? = null

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        getAttributes(attrs)

        stretchMode = GridView.STRETCH_COLUMN_WIDTH
        numColumns = column
    }

    private fun getAttributes(attrs: AttributeSet?) {
        if (null == attrs)
            return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideGridView)

        column = typedArray.getInt(R.styleable.SlideGridView_column, 0)
        numRows = typedArray.getInt(R.styleable.SlideGridView_row, 0)

        typedArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        remeasure(w, h)
        updateAdapter()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec)

        remeasure(width, height)

        setMeasuredDimension(width, height)
    }

    /**
     * use it to update view if you have changed width/height, grid size or adapter.
     */
    private fun updateAdapter() {
        if (null == slideGridAdapter)
            return

        slideGridAdapter!!.setNumRows(numRows)
        slideGridAdapter!!.setNumColumns(column)
        slideGridAdapter!!.setColumnHeight(itemHeight)
        slideGridAdapter!!.setColumnWidth(itemWidth)
        adapter = slideGridAdapter
    }

    fun update() {
        remeasure(measuredWidth, measuredHeight)
        updateAdapter()
    }

    /**
     * @param displayWidth  sets max available width for grid view
     * @param displayHeight sets max available height for grid view
     */
    fun setDimension(displayWidth: Float, displayHeight: Float) {
        itemWidth = displayWidth.toInt() / column
        itemHeight = displayHeight.toInt() / numRows
        updateAdapter()
    }

    private fun remeasure(width: Int, height: Int) {
        itemWidth = width / if (column == 0) 1 else column
        itemHeight = height / if (numRows == 0) 1 else numRows
    }

    /**
     * @return Number of columns associated with the view
     */
    override fun getNumColumns(): Int {
        return column
    }

    /**
     * @param column sets the desired number of columns in the grid
     */
    override fun setNumColumns(column: Int) {
        this.column = column
        super.setNumColumns(column)
    }

    /**
     * @param slideGridAdapter sets your adapter later in updateAdapter method.
     */
    fun setFitGridAdapter(slideGridAdapter: SlideGridAdapter) {
        this.slideGridAdapter = slideGridAdapter
    }
}