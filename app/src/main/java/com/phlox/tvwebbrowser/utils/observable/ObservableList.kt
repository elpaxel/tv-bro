package com.phlox.tvwebbrowser.utils.observable

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import java.util.function.Predicate
import java.util.function.UnaryOperator

typealias ListChangeObserver<T> = (ObservableList<T>) -> Unit

class ObservableList<T>(private val pushOnSubscribe: Boolean = true): ArrayList<T>(), Subscribable<ListChangeObserver<T>> {
  override val observers = ArrayList<ListChangeObserver<T>>()

  override fun subscribe(observer: ListChangeObserver<T>) {
    super.subscribe(observer)
    if (pushOnSubscribe) {
      observer(this)
    }
  }

  private fun notifyChanged() {
    for (observer in observers) {
      observer(this)
    }
  }

  override fun add(element: T): Boolean {
    val result = super.add(element)
    notifyChanged()
    return result
  }

  override fun add(index: Int, element: T) {
    super.add(index, element)
    notifyChanged()
  }

  override fun addAll(elements: Collection<T>): Boolean {
    val result = super.addAll(elements)
    notifyChanged()
    return result
  }

  override fun addAll(index: Int, elements: Collection<T>): Boolean {
    val result = super.addAll(index, elements)
    notifyChanged()
    return result
  }

  override fun clear() {
    super.clear()
    notifyChanged()
  }

  override fun remove(element: T): Boolean {
    val result = super.remove(element)
    notifyChanged()
    return result
  }

  override fun removeAll(elements: Collection<T>): Boolean {
    val result = super.removeAll(elements)
    notifyChanged()
    return result
  }

  override fun retainAll(elements: Collection<T>): Boolean {
    val result = super.retainAll(elements)
    notifyChanged()
    return result
  }

  @RequiresApi(VERSION_CODES.N)
  override fun removeIf(filter: Predicate<in T>): Boolean {
    val result = super.removeIf(filter)
    notifyChanged()
    return result
  }

  override fun removeAt(index: Int): T {
    val result = super.removeAt(index)
    notifyChanged()
    return result
  }

  override fun set(index: Int, element: T): T {
    val result = super.set(index, element)
    notifyChanged()
    return result
  }

  @RequiresApi(VERSION_CODES.N)
  override fun replaceAll(operator: UnaryOperator<T>) {
    super.replaceAll(operator)
    notifyChanged()
  }

  override fun removeRange(fromIndex: Int, toIndex: Int) {
    super.removeRange(fromIndex, toIndex)
    notifyChanged()
  }
}