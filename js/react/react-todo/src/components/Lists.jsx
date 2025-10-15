export default function Lists({ todoData, setTodoData }) {

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

    return <>
        {
            todoData.map((item) => (
                <div key={item.id} style={getStyle(item.completed)}>
                    <input type='checkbox' checked={item.completed} onChange={() => handleCompleteChange(item.id)} />
                    {item.title}
                    <button style={btnStyle} onClick={() => handleClick(item.id)}>X</button>
                </div>
            ))
        }
    </>
}