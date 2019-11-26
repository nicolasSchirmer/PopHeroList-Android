package com.nschirmer.pophero.util

/**
 *  Class dedicated to be an abstraction of the module calling name and packaging.
 * @param moduleName is the module name from the specific module. Normally appear on top
 * in the project tree.
 * @param packageName is the package name from the activity that you want to call\
 * of that specific module. Unfortunately Google do not fixed this hardcoded solution.
 * **/
enum class ModuleHelper(val moduleName: String, val packageName: String) {

    HERO_POP_LIST( "pop_list", "com.nschirmer.poplist.PopListActivity")

}