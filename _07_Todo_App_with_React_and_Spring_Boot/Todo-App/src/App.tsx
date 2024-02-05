import { FC } from "react";
import TodoApp from "./components/TodoApp";
import styles from "./App.module.css";

const App: FC = () => {
  return (
    <>
      <div className={styles.container}>
        <TodoApp />
      </div>
    </>
  );
};

export default App;
