import { useState } from 'react'
import './App.css'
import Lists from './components/Lists'
import Form from './components/Form'

function App() {

  const [value, setValue] = useState('')

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
          <Lists todoData={todoData} setTodoData={setTodoData} />
          <Form value={value} handleSubmit={handleSubmit} setValue={setValue}/>
        </div>
      </div>
    </>
  )
}

export default App
