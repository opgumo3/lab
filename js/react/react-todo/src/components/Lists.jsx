import List from "./List"

export default function Lists({ todoData, setTodoData }) {
    return <>
        {
            todoData.map((item) => (
                <List
                    key={item.id}
                    id={item.id}
                    title={item.title}
                    completed={item.completed}
                    todoData={todoData}
                    setTodoData={setTodoData}
                />
            ))
        }
    </>
}