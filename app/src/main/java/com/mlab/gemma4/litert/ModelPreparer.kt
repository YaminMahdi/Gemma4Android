package com.mlab.gemma4.litert

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object ModelPreparer {
    const val MODEL_ASSET_NAME = "gemma-4-E2B-it.litertlm"
    private const val COPY_CHUNK = 16L * 1024 * 1024  // 16MB per transfer

    /**
     * Main entry point: Prepares the model for use.
     * Uses FileDescriptor with FileChannel for zero-memory copy.
     * - Copies model from assets if needed
     * - Returns the model file path
     */
    suspend fun prepareModel(
        context: Context,
        onProgress: (Float) -> Unit = {},
    ): String = withContext(Dispatchers.IO) {
        val modelFile = File(context.filesDir, MODEL_ASSET_NAME)

        // Return immediately if model already exists
        if (modelFile.exists() && modelFile.length() > 0) {
            onProgress(1f)
            return@withContext modelFile.absolutePath
        }

        // Use AssetFileDescriptor for zero-copy transfer
        context.assets.openFd(MODEL_ASSET_NAME).use { assetFd ->
            val total = assetFd.length
            var copied = 0L

            FileOutputStream(modelFile).channel.use { outChannel ->
                FileInputStream(assetFd.fileDescriptor).channel.use { inChannel ->
                    // Position to the start of the asset
                    inChannel.position(assetFd.startOffset)

                    var transferred: Long
                    while (copied < total) {
                        transferred = outChannel.transferFrom(
                            inChannel,
                            copied,
                            (total - copied).coerceAtMost(COPY_CHUNK)
                        )
                        if (transferred <= 0) {
                            throw IOException("Asset copy stalled at $copied/$total bytes")
                        }
                        copied += transferred
                        onProgress(copied.toFloat() / total.toFloat())
                    }
                }
            }
        }

        onProgress(1f)
        modelFile.absolutePath
    }
}