function SearchBar({ search, setSearch, handleSearch }) {
  return (
    <div className="d-flex gap-2 my-4">
      <input
        type="text"
        className="form-control"
        placeholder="Search Meal"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <button
        className="btn btn-primary"
        onClick={handleSearch}
      >
        Search
      </button>
    </div>
  );
}

export default SearchBar;