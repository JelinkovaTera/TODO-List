<script setup>
</script>

<template>
  <div class="todo-app">
    <h1>TODO List</h1>
    <div class="content">
    <div class="table-div">
      <!-- Table with Entries -->
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Description</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="todo in todos" :key="todo.entry_id">
        <td>{{ todo.entry_id }}</td>
        <td>{{ todo.description }}</td>
        <td>{{ todo.status_name }}</td>
        <td>
          <div class="actions">
          <button @click="editTodo(todo)">Edit</button>
          <button @click="deleteTodo(todo.entry_id)">Delete</button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    </div>
    <!-- Add/Edit Form -->
    <div class="form-div">
    <form @submit.prevent="saveTodo">
      <div>
        <label for="entry_id">ID </label>
        <span>{{ currentTodo.entry_id }}</span>
      </div>
      <div>
        <label for="description">Description </label>
        <input v-model="currentTodo.description" type="text" id="description" required />
      </div>
      <div>
        <label for="status">Status </label>
        <select v-model="currentTodo.status_id" id="status">
          <option value="" disabled>Select a status</option>
          <option v-for="status in statuses" :key="status.status_id" :value="status.status_id">
            {{ status.status_name }}
          </option>
        </select>
      </div>
      <button type="submit">{{ isEdit ? 'Update' : 'Add' }} Todo</button>
      <button type="button" @click="resetForm"> Clear</button>
    </form>
    </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      todos: [],
      currentTodo: { id: null, description: '', status_id: ''},
      isEdit: false,
      statuses: [],
      selectedStatus: '',
    };
  },
  mounted() {
    this.fetchTodos();
  },
  created() {
    this.fetchStatuses();
  },

  methods: {
    async fetchTodos() {
      try {
        const response = await axios.get('http://localhost:8080/api/1.0/entries');
        this.todos = response.data;
      } catch (error) {
        console.error('Error fetching todos:', error);
      }
    },

    async fetchStatuses() {
      try {
        const response = await axios.get('http://localhost:8080/api/1.0/statuses');
        this.statuses = response.data;
      } catch (error) {
        console.error('Error fetching statuses:', error);
      }
    },

    // Save a new Entry or update an existing one
    async saveTodo() {
      if (this.isEdit) {
        // Update the existing Entry
        try {
          await axios.put(`http://localhost:8080/api/1.0/entries/${this.currentTodo.entry_id}`, this.currentTodo);
          this.fetchTodos();
          this.resetForm();
        } catch (error) {
          console.error('Error updating todo:', error);
        }
      } else {
        // Add a new Entry
        try {
          await axios.post('http://localhost:8080/api/1.0/entries', this.currentTodo);
          this.fetchTodos();
          this.resetForm();
        } catch (error) {
          console.error('Error adding todo:', error);
        }
      }
    },

    // Start editing an Entry
    editTodo(todo) {
      this.isEdit = true;
      this.currentTodo = { ...todo }; // Copy the data into the form
      const status = this.statuses.find(
          (status) => status.status_name === todo.status_name
      );
      this.currentTodo.status_id = status.status_id;
    },

    // Delete an Entry
    async deleteTodo(id) {
      try {
        await axios.delete(`http://localhost:8080/api/1.0/entries/${id}`);
        this.fetchTodos();
      } catch (error) {
        console.error('Error deleting todo:', error);
      }
    },

    // Reset the form
    resetForm() {
      this.currentTodo = { id: null, description: '', status_id: '' };
      this.selectedStatus = null;
      this.isEdit = false;
    },
  },

};
</script>

<style scoped>
.todo-app {
  box-sizing: border-box;
  max-width: none;
  width: 100%;
}

.actions {
  display: flex;
}

.content {
  box-sizing: border-box;
  max-width: none;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 30px;
  margin: 20px;
}

table {
  box-sizing: border-box;
  max-width: none;
  width: 65vw;
  border-collapse: collapse;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

th, td {
  padding: 6px 10px;
  text-align: left;
  font-size: 16px;
  color: #333;
}

th {
  background-color: #2575fc;
  color: white;
  font-weight: bold;
  text-transform: uppercase;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

tr:hover {
  background-color: #f1f1f1;
  transform: translateY(-2px);
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

td {
  border-top: 1px solid #ddd;
}

th, td {
  border-bottom: 1px solid #ddd;
}

th:nth-child(1), td:nth-child(1) {
  width: 10%;
}

th:nth-child(2), td:nth-child(2) {
  width: 40%;
}

th:nth-child(3), td:nth-child(3) {
  width: 20%;
}

th:nth-child(4), td:nth-child(4) {
  width: 20%;
}

caption {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 16px;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  background: linear-gradient(145deg, #6a11cb, #2575fc);
  color: white;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

button:hover {
  background: linear-gradient(145deg, #2575fc, #6a11cb);
  transform: translateY(-2px);
  box-shadow: 0px 6px 8px rgba(0, 0, 0, 0.1), 0px 2px 4px rgba(0, 0, 0, 0.08);
}

button:active {
  background: linear-gradient(145deg, #6a11cb, #2575fc);
  transform: translateY(0);
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

form {
  max-width: none;
  width: 20vw;
  padding: 20px;
  margin-left: auto;
  margin-right: auto;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

form label {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
  display: block;
  color: #333;
}

form input, form select {
  width: 100%;
  padding: 10px;
  margin-bottom: 15px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
  transition: all 0.3s ease;
}

form input:focus, form select:focus {
  border-color: #2575fc;
  outline: none;
  box-shadow: 0px 0px 5px rgba(37, 117, 252, 0.5);
}

form button {
  width: 100%;
  padding: 12px;
  font-size: 16px;
  font-weight: bold;
  background: linear-gradient(145deg, #6a11cb, #2575fc);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

form button:hover {
  background: linear-gradient(145deg, #2575fc, #6a11cb);
  transform: translateY(-2px);
  box-shadow: 0px 6px 8px rgba(0, 0, 0, 0.1), 0px 2px 4px rgba(0, 0, 0, 0.08);
}

form button:active {
  background: linear-gradient(145deg, #6a11cb, #2575fc);
  transform: translateY(0);
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.08);
}

select {
  padding: 10px 15px;
  font-size: 16px;
  width: 100%;
  max-width: 300px;
  margin-top: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #fff;
  color: #333;
  appearance: none;
  cursor: pointer;
  transition: all 0.3s ease;
  box-sizing: border-box;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

select:focus {
  border-color: #2575fc;
  outline: none;
  box-shadow: 0px 0px 5px rgba(37, 117, 252, 0.5);
}

select::after {
  content: '\25BC';
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  pointer-events: none;
}

select option {
  padding: 10px;
}
</style>
