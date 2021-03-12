package com.github.grahamsmith.darttest.actions

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleTypeWithWebFeatures
import com.intellij.openapi.project.Project
import com.intellij.psi.search.FileTypeIndex
import com.jetbrains.lang.dart.DartFileType
import com.jetbrains.lang.dart.sdk.DartSdk
import org.jetbrains.annotations.Nullable

class ActionHelper {

    companion object {

        fun isDartAvailable(context: DataContext): Boolean {

            val module = LangDataKeys.MODULE.getData(context) ?: return false

            return projectHasDartFiles(module) || hasDartSdk(module.project)
        }

        private fun projectHasDartFiles(module: @Nullable Module) =
            FileTypeIndex.containsFileOfType(DartFileType.INSTANCE, module.moduleContentScope)

        private fun hasDartSdk(project: Project): Boolean = DartSdk.getDartSdk(project) != null
    }
}