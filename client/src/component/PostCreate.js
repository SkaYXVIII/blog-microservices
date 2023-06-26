import React, {useState} from "react";
import API from "../fetch/axiosSetup";

const PostCreate = ({setPosts}) => {
    const [title, setTitle] = useState("");
    const onSubmit = async (event) => {
        event.preventDefault();
        await API.post('/posts-service/posts', {
            title
        });
        setTitle("");
        const res = await API.get('/posts-service/posts');
        setPosts(res.data);
    }
    return <div>
        <form onSubmit={onSubmit}>
            <div className="form-group">
                <label>Title</label>
                <input
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                    className="form-control"/>
            </div>
            <button className="btn btn-primary">Submit</button>
        </form>
    </div>;
}

export default PostCreate;