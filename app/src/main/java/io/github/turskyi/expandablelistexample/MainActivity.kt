package io.github.turskyi.expandablelistexample

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import java.util.*

class MainActivity : Activity() {
    // названия компаний (групп)
    var groups = arrayOf("HTC", "Samsung", "LG")

    // названия телефонов (элементов)
    var phonesHTC = arrayOf("Sensation", "Desire", "Wildfire", "Hero")
    var phonesSams = arrayOf("Galaxy S II", "Galaxy Nexus", "Wave")
    var phonesLG = arrayOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

    // коллекция для групп
    var groupData: ArrayList<Map<String, String?>>? = null

    // коллекция для элементов одной группы
    var childDataItem: ArrayList<Map<String, String?>>? = null

    // общая коллекция для коллекций элементов
    var childData: ArrayList<ArrayList<Map<String, String?>>>? = null

    // в итоге получится childData = ArrayList<childDataItem>
    // список атрибутов группы или элемента
    var m: MutableMap<String, String?>? = null
    var elvMain: ExpandableListView? = null

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // заполняем коллекцию групп из массива с названиями групп
        groupData = ArrayList()
        for (group in groups) {
            // заполняем список атрибутов для каждой группы
            m = HashMap()
            (m as HashMap<String, String?>)["groupName"] = group // имя компании
            groupData!!.add(m as HashMap<String, String?>)
        }

        // список атрибутов групп для чтения
        val groupFrom = arrayOf("groupName")
        // список ID view-элементов, в которые будет помещены атрибуты групп
        val groupTo = intArrayOf(android.R.id.text1)


        // создаем коллекцию для коллекций элементов
        childData = ArrayList()

        // создаем коллекцию элементов для первой группы
        childDataItem = ArrayList()
        // заполняем список атрибутов для каждого элемента
        for (phone in phonesHTC) {
            m = HashMap()
            (m as HashMap<String, String?>)["phoneName"] = phone // название телефона
            childDataItem!!.add(m as HashMap<String, String?>)
        }
        // добавляем в коллекцию коллекций
        childData!!.add(childDataItem!!)

        // создаем коллекцию элементов для второй группы
        childDataItem = ArrayList()
        for (phone in phonesSams) {
            m = HashMap()
            (m as HashMap<String, String?>)["phoneName"] = phone
            childDataItem!!.add(m as HashMap<String, String?>)
        }
        childData!!.add(childDataItem!!)

        // создаем коллекцию элементов для третьей группы
        childDataItem = ArrayList()
        for (phone in phonesLG) {
            m = HashMap()
            (m as HashMap<String, String?>)["phoneName"] = phone
            childDataItem!!.add(m as HashMap<String, String?>)
        }
        childData!!.add(childDataItem!!)

        // список атрибутов элементов для чтения
        val childFrom = arrayOf("phoneName")
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        val childTo = intArrayOf(android.R.id.text1)
        val adapter = SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo)
        elvMain = findViewById<View>(R.id.elvMain) as ExpandableListView
        elvMain!!.setAdapter(adapter)
    }
}