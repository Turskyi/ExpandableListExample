package io.github.turskyi.expandablelistexample

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    /** names of companies (groups) */
    var groups = arrayOf("HTC", "Samsung", "LG")

    /** phone (item) names */
    private var phonesHTC = arrayOf("Sensation", "Desire", "Wildfire", "Hero")
    private var phonesSams = arrayOf("Galaxy S II", "Galaxy Nexus", "Wave")
    private var phonesLG = arrayOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

    /** collection for groups */
    private var groupData: ArrayList<Map<String, String?>>? = null

    /** collection for elements of one group */
    var childDataItem: ArrayList<Map<String, String?>>? = null

    /** general collection for item collections */
    var childData: ArrayList<ArrayList<Map<String, String?>>>? = null

    /*
    * as a result, we get childData = ArrayList <childDataItem> ;
    list of group or element attributes
    * */
    var m: MutableMap<String, String?>? = null
    var elvMain: ExpandableListView? = null

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** fill in the collection of groups from an array with group names */
        groupData = ArrayList()
        for (group in groups) {
            /** fill in the list of attributes for each group */
            m = HashMap()
            /* company name */
            (m as HashMap<String, String?>)["groupName"] = group
            groupData?.add(m as HashMap<String, String?>)
        }

        /* list of group attributes to read */
        val groupFrom = arrayOf("groupName")
        /* list of view-element IDs, in which group attributes will be placed */
        val groupTo = intArrayOf(android.R.id.text1)

        /* create a collection for item collections */
        childData = ArrayList()

        /* create a collection of items for the first group */
        childDataItem = ArrayList()
        /* fill in the list of attributes for each element */
        for (phone in phonesHTC) {
            m = HashMap()
            /* phone name */
            (m as HashMap<String, String?>)["phoneName"] = phone
            childDataItem?.add(m as HashMap<String, String?>)
        }
        /* adding the collection of collections */
        if (childDataItem != null) {
            childData?.add(childDataItem!!)
        }

        /* creating a collection of items for the second group */
        childDataItem = ArrayList()
        for (phone in phonesSams) {
            m = HashMap()
            (m as HashMap<String, String?>)["phoneName"] = phone
            childDataItem?.add(m as HashMap<String, String?>)
        }
        if (childDataItem != null) {
            childData?.add(childDataItem!!)
        }

        /* creating a collection of elements for the third group */
        childDataItem = ArrayList()
        for (phone in phonesLG) {
            m = HashMap()
            (m as HashMap<String, String?>)["phoneName"] = phone
            childDataItem?.add(m as HashMap<String, String?>)
        }

        if (childDataItem != null) {
            childData?.add(childDataItem!!)
        }

        /* list of element attributes to read */
        val childFrom = arrayOf("phoneName")
        /* list of ID view-elements, which will contain the attributes of the elements */
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
        elvMain?.setAdapter(adapter)
    }
}