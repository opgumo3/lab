import { useState } from 'react'
import './App.css'

function App() {

  const btnStyle = {
    color: '#fff',
    border: 'none',
    padding: '5px 9px',
    borderRadius: '50%',
    cursor: 'pointer',
    float: 'right',
  }

  const getStyle = (completed) => {
    return {
      padding: '10px',
      borderBottom: '1px #ccc dotted',
      textDecoration: completed ? 'line-through' : 'none',
    }
  }

  const initialData = [
    {
      id: 1,
      title: 'task1',
      completed: true,
    },
    {
      id: 2,
      title: 'task2',
      completed: false,
    }
  ]

  const [todoData, setTodoData] = useState(initialData)
  const [value, setValue] = useState('')


  const handleClick = (id) => {
    let newData = todoData.filter(item => item.id !== id)
    setTodoData(newData)
  }

  const handleChange = (e) => {
    setValue(e.target.value)
  }

  const handleSubmit = (e) => {
    e.preventDefault()

    let newTodo = {
      id: Date.now(),
      title: value,
      completed: false,
    }

    setTodoData([...todoData, newTodo]);
    setValue('')
  }

  const handleCompleteChange = (id) => {
    todoData.map(item => {
      if (item.id === id) {
        item.completed = !item.completed
      }
      return item
    })

    setTodoData([...todoData])
  }

  return (
    <>
      <div className='container'>
        <div className='todoBlock'>
          <h1 className='title'>TodoList</h1>

          <form style={{ display: 'flex' }} onSubmit={handleSubmit}>
            <input
              type='text'
              name='value'
              style={{ flex: '10', padding: '5px' }}
              placeholder='할 일을 입력하세요'
              value={value}
              onChange={(e) => handleChange(e)}
            />
            <input
              type='submit'
              value='입력'
              className='btn'
              style={{ flex: '1' }}
            />
          </form>

          {
            todoData.map((item) => (
              <div key={item.id} style={getStyle(item.completed)}>
                <input type='checkbox' checked={item.completed}onChange={() => handleCompleteChange(item.id)}/>
                {item.title}
                <button style={btnStyle} onClick={() => handleClick(item.id)}>X</button>
              </div>
            ))
          }
        </div>
      </div>
    </>
  )
}

export default App
