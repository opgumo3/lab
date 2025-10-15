import { useState } from 'react'
import './App.css'
import Lists from './components/Lists'

function App() {

  const [todoData, setTodoData] = useState([
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
  ])
  const [value, setValue] = useState('')

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

          <Lists todoData={todoData} setTodoData={setTodoData} />
        </div>
      </div>
    </>
  )
}

export default App
