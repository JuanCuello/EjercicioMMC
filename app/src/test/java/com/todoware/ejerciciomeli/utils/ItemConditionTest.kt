package com.todoware.ejerciciomeli.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemConditionTest {

    @Test
    fun testNavigationType_conditionNew() {
        val response = ItemCondition.getConditionByName("new")
        assertEquals(response, ItemCondition.NEW.resourceId)
    }

    @Test
    fun testNavigationType_conditionUsed() {
        val response = ItemCondition.getConditionByName("used")
        assertEquals(response, ItemCondition.USED.resourceId)
    }

    @Test
    fun testNavigationType_conditionRefurbished() {
        val response = ItemCondition.getConditionByName("refurbished")
        assertEquals(response, ItemCondition.REFURBISHED.resourceId)
    }

    @Test
    fun testNavigationType_conditionUnsupported() {
        val response = ItemCondition.getConditionByName("imported")
        assertEquals(response, ItemCondition.UNSUPPORTED.resourceId)
    }


}