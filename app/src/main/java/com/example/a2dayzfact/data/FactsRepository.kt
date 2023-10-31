package com.example.a2dayzfact.data

import com.example.a2dayzfact.data.api.WikiApi
import com.example.a2dayzfact.data.model.FactRemoteEntity
import com.example.a2dayzfact.data.model.WikiFactEntity
import com.example.a2dayzfact.di.module.IoDispatcher
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class FactsRepository @Inject constructor(
    firebaseDatabase: FirebaseDatabase,
    private val wikiApi: WikiApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    companion object {
        private const val FACT_TABLE = "facts"
    }

    private val factTable = firebaseDatabase
        .getReference(FACT_TABLE)

    suspend fun getFactsForDay(dayMonth: String): List<FactRemoteEntity?> = withContext(dispatcher) {
        suspendCoroutine { continuation ->
            factTable.child(dayMonth).orderByKey()
                .addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val result = snapshot.children.map {
                            it.getValue(FactRemoteEntity::class.java)
                        }
                        continuation.resume(result)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        continuation.resumeWithException(error.toException())
                    }
                })
        }
    }

    suspend fun getFactsFromWiki(day: Int, month: Int): List<WikiFactEntity> = withContext(dispatcher) {
        wikiApi.getFacts(
            month = "${if (month < 10) "0$month" else month}",
            day = "${if (day < 10) "0$day" else day}"
        ).facts
    }
}
