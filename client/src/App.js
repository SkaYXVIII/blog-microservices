import React,{ useState } from "react";
import PostCreate from "./component/PostCreate";
import PostList from "./component/PostList";

const App = () => {
    const [posts, setPosts] = useState({});

    return <div className="container">
        <h1>Create Post</h1>
        <PostCreate setPosts={setPosts}/>
        <hr/>
        <h1>Posts</h1>
        <PostList posts={posts} setPosts={setPosts}/>
    </div>;
}

export default App;