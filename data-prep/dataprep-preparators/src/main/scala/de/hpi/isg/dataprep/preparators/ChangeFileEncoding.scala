package de.hpi.isg.dataprep.preparators

import de.hpi.isg.dataprep.model.target.preparator.Preparator

/**
  *
  * @author Lan Jiang
  * @since 2018/9/2
  */
class ChangeFileEncoding(val sourceEncoding : String,
                         val targetEncoding : String) extends Preparator {

    /**
      * This method validates the input parameters of a [[Preparator]]. If succeeds, setup the values of metadata into both
      * prerequisite and toChange set.
      *
      * @throws Exception
      */
    override def buildMetadataSetup(): Unit = {

    }
}