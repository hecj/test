package scallop.center.dao;

import java.util.List;

import scallop.api.ScallopServerDaoException;

public abstract interface ResourceDao
{
  public abstract Resource getResourceByName(String paramString)
    throws ScallopServerDaoException;
  public abstract List<Resource> getAllResource()
		    throws ScallopServerDaoException;
}

/* Location:           C:\Users\hechaojie\Desktop\scallop-center-dist\classes\
 * Qualified Name:     scallop.center.dao.ResourceDao
 * JD-Core Version:    0.6.1
 */