package com.task.app

interface TaskDao {
    fun getCurrentTask(userId: UserId): Task
}

class SqlTaskDao(): TaskDao {
    override fun getCurrentTask(userId: UserId): Task {
        TODO()
    }

}