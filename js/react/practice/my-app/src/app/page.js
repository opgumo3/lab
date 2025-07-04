import Profile from "../component/Profile";
import ShoppingList from "@/component/ShoppingList";
import ButtonEvent from "@/component/ButtonEvent";
import StateButton from "@/component/State";

export default function Home() {
  return (
    <div>
      <div className="container">
        <h3>Nesting component</h3>
        <Profile/>
      </div>
      <div className="container">
        <h3>Rendering lists</h3>
        <ShoppingList/>
      </div>
      <div className="container">
        <h3>Responding to events</h3>
        <ButtonEvent/>
      </div>
      <div className="container">
        <h3>Using state</h3>
        <StateButton/>
      </div>
    </div>
  );
}
