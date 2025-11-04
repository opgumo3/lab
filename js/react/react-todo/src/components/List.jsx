import { useState } from 'react';

export default function List({ id, title, completed, todoData, setTodoData }) {

    const [isEditing, setIsEditing] = useState(false);

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


    const handleClick = (id) => {
        let newData = todoData.filter(item => item.id !== id)
        setTodoData(newData)
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

    return <div>
        <div key={id} style={getStyle(completed)}>
            <input type='checkbox' checked={completed} onChange={() => handleCompleteChange(id)} />
            {title}
            <button style={btnStyle} onClick={() => handleClick(id)}>X</button>
            <button style={btnStyle}>Edit</button>
        </div>
    </div>
}