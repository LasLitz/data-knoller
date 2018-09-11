package de.hpi.isg.dataprep.preparators

import de.hpi.isg.dataprep.implementation.DefaultMovePropertyImpl
import de.hpi.isg.dataprep.model.target.preparator.Preparator

/**
  *
  * @author Lan Jiang
  * @since 2018/8/31
  */
class MoveProperty(val propertyName : String,
                   val newPosition : Int) extends Preparator {

    def this(propertyName : String) {
        this(propertyName, MoveProperty.DEFAULT_NEW_POSITION)
    }

    this.impl = new DefaultMovePropertyImpl

    /**
      * This method validates the input parameters of a [[Preparator]]. If succeeds, setup the values of metadata into both
      * prerequisite and toChange set.
      *
      * @throws Exception
      */
    override def buildMetadataSetup(): Unit = {

    }
}

object MoveProperty {
    val DEFAULT_NEW_POSITION = 0
}
