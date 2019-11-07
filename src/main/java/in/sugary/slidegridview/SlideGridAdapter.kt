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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter

abstract class SlideGridAdapter : BaseAdapter {

    private var context: Context? = null
    private var columnWidth: Int = 0
    private var columnHeight: Int = 0
    private var row: Int = 0
    private var column: Int = 0
    private var itemId: Int = 0
    private var size = 0

    constructor(context: Context) {
        this.context = context
    }

    constructor(context: Context, itemId: Int) {
        this.context = context
        this.itemId = itemId
    }

    constructor(context: Context, itemId: Int, size: Int) {
        this.context = context
        this.itemId = itemId

        this.size = size
    }

    constructor(context: Context, itemId: Int, row: Int, column: Int) {
        this.context = context
        this.itemId = itemId
        this.row = row
        this.column = column

        size = row * column
    }

    override fun getView(position: Int, itemView: View?, parent: ViewGroup): View {
        var itemView = itemView
        if (itemView == null) {
            val inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(itemId, parent, false)

            val params = AbsListView.LayoutParams(columnWidth, columnHeight)
            itemView!!.layoutParams = params

            onBindView(position, itemView)
        }
        return itemView
    }

    abstract fun onBindView(position: Int, view: View)

    override fun getCount(): Int {
        return if (size == 0) column * row else size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    internal fun setColumnHeight(columnHeight: Int) {
        this.columnHeight = columnHeight
    }

    internal fun setColumnWidth(columnWidth: Int) {
        this.columnWidth = columnWidth
    }

    internal fun setNumColumns(column: Int) {
        this.column = column
    }

    internal fun setNumRows(row: Int) {
        this.row = row
    }
}
